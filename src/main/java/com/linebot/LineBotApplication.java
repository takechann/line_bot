/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linebot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import com.linebot.fielder.Fielder;
import com.linebot.fielder.FielderService;
import com.linebot.pitcher.Pitcher;
import com.linebot.pitcher.PitcherService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@LineMessageHandler
public class LineBotApplication {
    public static Path downloadedContentDir;

    Logger logger = Logger.getLogger(LineBotApplication.class.getName());

    // 野手検索サービス
    @Autowired
    FielderService fielderService;

    // 投手検索サービス
    @Autowired
    PitcherService pitcherService;

    public static void main(String[] args) throws IOException {
        downloadedContentDir = Files.createTempDirectory("line-bot");
        final SpringApplication springApplication = new SpringApplication(LineBotApplication.class);
        springApplication.run(args);
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        // 入力値
        String selectName = event.getMessage().getText();
        System.out.println("selectName: " + selectName);

        // 野手のほうが候補数が多いので、野手で検索する
        List<Fielder> fielderSelectList = fielderService.selectName(event.getMessage().getText());
        logger.info("fielderSelectList: " + fielderSelectList);

        if(fielderSelectList.size() == 0) {
            // 該当者がいない場合
            logger.info("該当者がいません");
            return new TextMessage("該当者がいません。もう一度検索してください。");
        } else if(fielderSelectList.size() > 1) {
            // 該当者が複数いる場合
            // TODO: 名前を選択させる
            return new TextMessage("該当者が複数いるので、選択してください。");
        }

        // 以降の処理は該当者1件のケースとなる
        // 該当者が投手かもしれないので、投手で検索する
        List<Pitcher> pitcherSelectList = pitcherService.selectName(event.getMessage().getText());
        logger.info("pitcherSelectList: " + pitcherSelectList);

        // 投手 or 野手
        StringBuilder message = new StringBuilder();
        if(pitcherSelectList.size() == 0) {
            // 野手の場合
            logger.info("野手です");

            message.append(fielderSelectList.get(0).getName() + "（野手）\n");
            message.append("打率：" + fielderSelectList.get(0).getBatting_average() + "\n");
            message.append("打数：" + fielderSelectList.get(0).getAt_bats() + "\n");
            message.append("安打：" + fielderSelectList.get(0).getHit() + "\n");
            message.append("本塁打：" + fielderSelectList.get(0).getHome_run() + "\n");
            message.append("打点：" + fielderSelectList.get(0).getRbi() + "\n");
            message.append("OPS：" + fielderSelectList.get(0).getOps());
            return new TextMessage(message.toString());
        } else if(pitcherSelectList.size() == 1) {
            // 投手
            logger.info("投手です");

            message.append(pitcherSelectList.get(0).getName() + "（投手）\n");
            message.append("防御率：" + pitcherSelectList.get(0).getEra() + "\n");
            message.append("投球回：" + pitcherSelectList.get(0).getPitching_times() + "\n");
            message.append("勝利：" + pitcherSelectList.get(0).getWinning() + "\n");
            message.append("敗戦：" + pitcherSelectList.get(0).getDefeat() + "\n");
            message.append("ホールド：" + pitcherSelectList.get(0).getHold() + "\n");
            message.append("セーブ：" + pitcherSelectList.get(0).getSave() + "\n");
            message.append("奪三振：" + pitcherSelectList.get(0).getStrikeout());

            return new TextMessage(message.toString());
        }
        // エラー
        return new TextMessage("エラーが発生しました。もう一度検索してください。");

    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
