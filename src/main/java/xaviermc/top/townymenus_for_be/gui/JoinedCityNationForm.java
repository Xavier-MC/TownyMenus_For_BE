package xaviermc.top.townymenus_for_be.gui;

import org.bukkit.entity.Player;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

import static xaviermc.top.townymenus_for_be.gui.MainForm.sendMainForm;
import static xaviermc.top.townymenus_for_be.gui.manager.ManagerForm.sendManagerForm;
import static xaviermc.top.townymenus_for_be.gui.player.PlayerForm.sendPlayerForm;
import static xaviermc.top.townymenus_for_be.gui.player.city.CityForm.sendCityForm;
import static xaviermc.top.townymenus_for_be.gui.player.nation.NationForm.sendNationForm;

public class JoinedCityNationForm {
    public static void sendJoinedCityNationForm(Player player){
        UUID uuid = player.getUniqueId();
        if (!FloodgateApi.getInstance().isFloodgatePlayer(uuid))return;
        FloodgateApi.getInstance().getPlayer(uuid).sendForm(
                SimpleForm.builder()
                        .title("国家/城市菜单")
                        .button("我是管理者")
                        .button("我是普通玩家")
                        .button("返回上一页")
                        .responseHandler((f, r) -> {
                            SimpleFormResponse response = f.parseResponse(r);
                            if (response.isCorrect()) {
                                int id = response.getClickedButtonId();
                                if (id == 0) {
                                    sendManagerForm(player);
                                } else if (id == 1) {
                                    sendPlayerForm(player);
                                } else if (id == 2) {
                                    sendMainForm(player);
                                }
                            }
                        })
        );
    }
}