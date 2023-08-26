package xaviermc.top.townymenus_for_be.gui;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.ModalForm;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.cumulus.response.ModalFormResponse;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

import static xaviermc.top.townymenus_for_be.gui.MainForm.sendMainForm;

import xaviermc.top.townymenus_for_be.TownyMenus_For_BE;
import xaviermc.top.townymenus_for_be.utils.*;

public class NotJoinedCityNationForm {
    public static void sendNotJoinedCityNationForm(Player player) {
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
    }


    private static void sendCreateOwnCityForm(Player player) {
        UUID uuid = player.getUniqueId();
        String townyadvanced_town_creation_cost = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_town_creation_cost%");
        FloodgateApi.getInstance().getPlayer(uuid).sendForm(
                ModalForm.builder()
                        .title("创建属于你的城市")
                        .content("是否选择创建你的城市，您的城市初始领地为您脚下所在区块 \n预计成本为" + townyadvanced_town_creation_cost)
                        .button1("确认")
                        .button2("取消")
                        .responseHandler((f, r) -> {
                            ModalFormResponse response = f.parseResponse(r);
                            if (response.isCorrect()) {
                                int id = response.getClickedButtonId();
                                if (id == 0) {
                                    sendCreateCityForm(player);
                                } else if (id == 1) {
                                    sendNotJoinedCityNationForm(player);
                                }
                            }
                        })
                        .build()
        );
    }

    public static void sendCreateCityForm(Player player) {
        UUID uuid = player.getUniqueId();
        FloodgateApi.getInstance().getPlayer(uuid).sendForm(
                CustomForm.builder()
                        .title("创建城市")
                        .input("请输入你的城市名字", "仅支持输入英文和数字")
                        .responseHandler((form, responseData) -> {
                            CustomFormResponse response = form.parseResponse(responseData);
                            String cityName = response.getInput(0);
                            if (!InputFormatChecker.isValidCityName(cityName)) {
                                player.sendMessage(ChatColor.RED + "城市名字只能包含英文和数字，将重新发送表单，请重新填写。");
                                    sendCreateCityForm(player);
                                return;
                            }
                            player.performCommand("town new " + cityName);
                        })
                        .build()
        );
    }
}