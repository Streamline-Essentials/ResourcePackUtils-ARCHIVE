package tv.quaint.runnables;

import lombok.Getter;
import lombok.Setter;
import net.streamline.api.SLAPI;
import net.streamline.api.interfaces.IStreamline;
import net.streamline.api.messages.ResourcePackMessageBuilder;
import net.streamline.api.modules.ModuleUtils;
import net.streamline.api.modules.StreamlineModule;
import net.streamline.api.objects.StreamlineResourcePack;
import net.streamline.api.savables.users.StreamlinePlayer;
import net.streamline.api.scheduler.ModuleRunnable;
import tv.quaint.ResourcePackUtils;

public class PackTicker extends ModuleRunnable {
    @Getter
    private final StreamlinePlayer player;
    @Getter
    private final IStreamline.PlatformType type;
    @Getter
    private final StreamlineResourcePack pack;

    public PackTicker(StreamlinePlayer player, IStreamline.PlatformType type, StreamlineResourcePack pack) {
        super(ResourcePackUtils.getInstance(), 20, 1);
        this.player = player;
        this.type = type;
        this.pack = pack;
    }

    @Override
    public void run() {
        switch (type) {
            case BUNGEE -> {
                if (ResourcePackUtils.getConfigs().isNetworkHandled()) {
                    SLAPI.getInstance().getProxyMessenger().sendMessage(ResourcePackMessageBuilder.build(player, pack));
                } else {
                    // do nothing;
                }
            }
            case VELOCITY -> {
                if (ResourcePackUtils.getConfigs().isNetworkHandled()) {
                    ModuleUtils.sendResourcePack(pack, player);
                } else {
                    // do nothing;
                }
            }
            case SPIGOT -> {
                if (ResourcePackUtils.getConfigs().isNetworkHandled()) {
                    // do nothing
                } else {
                    ModuleUtils.sendResourcePack(pack, player);
                }
            }
        }

        cancel();
    }
}
