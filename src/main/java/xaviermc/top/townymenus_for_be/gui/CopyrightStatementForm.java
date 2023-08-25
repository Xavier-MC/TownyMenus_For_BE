package xaviermc.top.townymenus_for_be.gui;

import org.bukkit.entity.Player;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

import static xaviermc.top.townymenus_for_be.gui.MainForm.sendMainForm;


public class CopyrightStatementForm {
    public static void sendCopyrightStatementForm(Player player) {
        UUID uuid = player.getUniqueId();
        if (!FloodgateApi.getInstance().isFloodgatePlayer(uuid)) return;
        FloodgateApi.getInstance().getPlayer(uuid).sendForm(
                SimpleForm.builder()
                        .title("版权声明")
                        .content("§a本插件由XavierMC团队开发\n§b本插件按照§eBSD 3-Clause License§b协议开源在Github\n§cwiki部分依据§dCC BY-SA 4.0§c协议\n§e引用自§fhttps://mineplugin.org/Towny/%E6%95%99%E7%A8%8B\n")
                        .button("返回上一页")
                        .responseHandler((f, r) -> {
                            SimpleFormResponse response = f.parseResponse(r);
                            if (response.isCorrect()) {
                                int id = response.getClickedButtonId();
                                if (id == 0) {
                                    sendMainForm(player);
                                }
                            }
                        })
        );
    }
}