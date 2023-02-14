package tv.quaint.runnables;

import lombok.Getter;
import lombok.Setter;
import net.streamline.api.SLAPI;
import net.streamline.api.interfaces.IStreamline;
import net.streamline.api.messages.builders.ResourcePackMessageBuilder;
import net.streamline.api.modules.ModuleUtils;
import net.streamline.api.objects.StreamlineResourcePack;
import net.streamline.api.savables.users.StreamlinePlayer;
import net.streamline.api.scheduler.ModuleRunnable;
import tv.quaint.ResourcePackUtils;

public class PackTicker extends ModuleRunnable {
    @Getter @Setter
    private StreamlinePlayer player;
    @Getter @Setter
    private IStreamline.PlatformType type;
    @Getter @Setter
    private StreamlineResourcePack pack;
    @Getter @Setter
    private int times;

    public PackTicker(StreamlinePlayer player, IStreamline.PlatformType type, StreamlineResourcePack pack) {
        super(ResourcePackUtils.getInstance(), 0, ResourcePackUtils.getConfigs().connectWait());
        this.player = player;
        this.type = type;
        this.pack = pack;
        this.times = 0;
    }

    @Override
    public void run() {
        times ++;
        if (times <= 1) {
            return;
        }

        switch (type) {
            case BUNGEE:
                if (ResourcePackUtils.getConfigs().isNetworkHandled()) {
                    SLAPI.getInstance().getProxyMessenger().sendMessage(
                            ResourcePackMessageBuilder.build(player,
                                    SLAPI.getInstance().getPlatform().getServerType().equals(IStreamline.ServerType.PROXY), player, pack));
                } else {
                    // do nothing;
                }
                break;
            case VELOCITY:
                if (ResourcePackUtils.getConfigs().isNetworkHandled()) {
                    ModuleUtils.sendResourcePack(pack, player);
                } else {
                    // do nothing;
                }
                break;
            case SPIGOT:
                if (ResourcePackUtils.getConfigs().isNetworkHandled()) {
                    // do nothing
                } else {
                    ModuleUtils.sendResourcePack(pack, player);
                }
                break;
        }

        cancel();
    }
}
