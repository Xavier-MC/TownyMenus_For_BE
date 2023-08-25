package xaviermc.top.townymenus_for_be.gui;

import org.bukkit.entity.Player;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

import xaviermc.top.townymenus_for_be.gui.*;

import static xaviermc.top.townymenus_for_be.gui.CopyrightStatementForm.sendCopyrightStatementForm;
import static xaviermc.top.townymenus_for_be.gui.JoinedCityNationForm.sendJoinedCityNationForm;
import static xaviermc.top.townymenus_for_be.gui.NotJoinedCityNationForm.sendNotJoinedCityNationForm;
import static xaviermc.top.townymenus_for_be.gui.WikiSystemForm.sendWikiSystemForm;


public class MainForm {
    public static void sendMainForm(Player player) {
        UUID uuid = player.getUniqueId();
        if (!FloodgateApi.getInstance().isFloodgatePlayer(uuid)) return;
        FloodgateApi.getInstance().getPlayer(uuid).sendForm(
                SimpleForm.builder()
                        .title("主菜单")
                        .button("已加入城市/国家")
                        .button("未加入城市/国家")
                        .button("wiki系统")
                        .button("版权声明")
                        .responseHandler((f, r) -> {
                            SimpleFormResponse response = f.parseResponse(r);
                            if (response.isCorrect()) {
                                int id = response.getClickedButtonId();
                                if (id == 0) {
                                    sendJoinedCityNationForm(player);
                                } else if (id == 1) {
                                    sendNotJoinedCityNationForm(player);
                                } else if (id == 2) {
                                    sendWikiSystemForm(player);
                                } else if (id == 3) {
                                    sendCopyrightStatementForm(player);
                                }
                            }
                        })
        );
    }
}
