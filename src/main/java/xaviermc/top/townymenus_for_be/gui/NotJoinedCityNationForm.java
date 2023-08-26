package xaviermc.top.townymenus_for_be.gui;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.ModalForm;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

import static xaviermc.top.townymenus_for_be.gui.MainForm.sendMainForm;


public class NotJoinedCityNationForm {
    public static void sendNotJoinedCityNationForm(Player player){
            UUID uuid = player.getUniqueId();
            if (!FloodgateApi.getInstance().isFloodgatePlayer(uuid)) return;
            FloodgateApi.getInstance().getPlayer(uuid).sendForm(
                    SimpleForm.builder()
                            .title("加入/新建城市")
                            .button("创建一个属于你的城市")
                            .button("加入别人的城市")
                            .button("返回上一页")
                            .responseHandler((f, r) -> {
                                SimpleFormResponse response = f.parseResponse(r);
                                if (response.isCorrect()) {
                                    int id = response.getClickedButtonId();
                                    if (id == 0) {
                                        sendCreateOwnCityForm(player);
                                    } else if (id == 1) {
                                        sendJoinOtherCityForm(player);
                                    } else if (id == 2) {
                                        sendMainForm(player);
                                    }
                                }
                            })
            );
        }

    private static void sendJoinOtherCityForm(Player player) {
        UUID uuid =player.getUniqueId();
        string townyadvanced_town_creation_cost = PlaceholderAPI.setPlaceholders(player,%townyadvanced_town_creation_cost%);
        FloodgateApi.getInstance().getPlayer(uuid).sendForm(
                ModalForm.builder()
                        .title("创建属于你的城市")
                        .content("是否选择创建你的城市，预计成本为")

        )
    }
    }

    private static void sendCreateOwnCityForm(Player player) {
    }

