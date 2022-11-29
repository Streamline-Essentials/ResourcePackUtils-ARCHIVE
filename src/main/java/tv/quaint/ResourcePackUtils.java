package tv.quaint;

import lombok.Getter;
import net.streamline.api.modules.ModuleUtils;
import net.streamline.api.modules.SimpleModule;
import org.pf4j.PluginWrapper;
import tv.quaint.configs.Configs;
import tv.quaint.listeners.MainListener;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public class ResourcePackUtils extends SimpleModule {
    @Getter
    private static ResourcePackUtils instance;
    @Getter
    private static Configs configs;
    @Getter
    private static MainListener mainListener;

    public ResourcePackUtils(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public ConcurrentSkipListSet<String> authors() {
        return new ConcurrentSkipListSet<>(List.of("Quaint"));
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        configs = new Configs();
        mainListener = new MainListener();
        ModuleUtils.listen(mainListener, this);
    }
}
