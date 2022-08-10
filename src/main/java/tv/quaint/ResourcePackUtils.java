package tv.quaint;

import lombok.Getter;
import net.streamline.api.modules.ModuleUtils;
import net.streamline.api.modules.SimpleModule;
import net.streamline.api.modules.dependencies.Dependency;
import tv.quaint.configs.Configs;
import tv.quaint.listeners.MainListener;

import java.util.List;

public class ResourcePackUtils extends SimpleModule {
    @Getter
    private static ResourcePackUtils instance;
    @Getter
    private static Configs configs;
    @Getter
    private static MainListener mainListener;

    @Override
    public String identifier() {
        return "resource-pack-utils";
    }

    @Override
    public List<String> authors() {
        return List.of("Quaint");
    }

    @Override
    public List<Dependency> dependencies() {
        return List.of();
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
