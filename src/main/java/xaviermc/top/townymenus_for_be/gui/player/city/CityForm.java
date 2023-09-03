package xaviermc.top.townymenus_for_be.gui.player.city;

import org.bukkit.entity.Player;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

public class CityForm {
    public static  void sendCityForm(Player player){
        UUID uuid = player.getUniqueId();
        if (!FloodgateApi.getInstance().isFloodgatePlayer(uuid)) return;
//        FloodgateApi.getInstance().getPlayer(uuid).sendForm();
        //待开发ing
    }
}
