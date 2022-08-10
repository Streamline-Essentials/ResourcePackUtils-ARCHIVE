package tv.quaint.listeners;

import lombok.Getter;
import lombok.Setter;
import net.streamline.api.SLAPI;
import net.streamline.api.events.EventProcessor;
import net.streamline.api.events.StreamlineListener;
import net.streamline.api.events.server.LoginCompletedEvent;
import net.streamline.api.events.server.LogoutEvent;
import net.streamline.api.interfaces.IStreamline;
import net.streamline.api.messages.ProxyMessageEvent;
import net.streamline.api.messages.ProxyMessageIn;
import net.streamline.api.messages.ResourcePackMessageBuilder;
import net.streamline.api.modules.ModuleUtils;
import net.streamline.api.objects.SingleSet;
import net.streamline.api.objects.StreamlineResourcePack;
import net.streamline.api.savables.users.StreamlinePlayer;
import tv.quaint.ResourcePackUtils;
import tv.quaint.runnables.PackTicker;

import java.util.concurrent.ConcurrentHashMap;

public class MainListener implements StreamlineListener {
    @Getter @Setter
    private static ConcurrentHashMap<StreamlinePlayer, Boolean> packedMap = new ConcurrentHashMap<>();

    @EventProcessor
    public void onJoin(LoginCompletedEvent event) {
        StreamlinePlayer player = event.getResource();
        IStreamline.PlatformType type = ModuleUtils.getPlatformType();
        StreamlineResourcePack pack = ResourcePackUtils.getConfigs().getResourcePack();
        new PackTicker(player, type, pack);
    }

    @EventProcessor
    public void onLeave(LogoutEvent event) {
        StreamlinePlayer player = event.getResource();
        getPackedMap().remove(player);
    }
}
