package tv.quaint.configs;

import net.streamline.api.configs.ModularizedConfig;
import net.streamline.api.objects.StreamlineResourcePack;
import org.apache.commons.codec.binary.Hex;
import tv.quaint.ResourcePackUtils;

public class Configs extends ModularizedConfig {
    public Configs() {
        super(ResourcePackUtils.getInstance(), "config.yml", true);
        init();
    }

    @Override
    public void init() {
        getResourcePack();
        isNetworkHandled();
        connectWait();
    }

    public StreamlineResourcePack getResourcePack() {
        reloadResource();

        String url = getResource().getOrSetDefault("pack.url", "https://linktopack.com");
        String prompt = getResource().getOrSetDefault("pack.prompt", "&eWe recommend using our pack.");
        String hashString = getResource().getOrSetDefault("pack.hash", "hashForPack");
        boolean force = getResource().getOrSetDefault("pack.force", false);
        byte[] hash;
        try {
            if (hashString.equals("")) {
                hash = new byte[0];
            } else {
                hash = Hex.decodeHex(hashString);
            }
        } catch (Exception e) {
            e.printStackTrace();
            hash = new byte[0];
        }

        return new StreamlineResourcePack(url, hash, prompt, force);
    }

    public boolean isNetworkHandled() {
        reloadResource();

        return getResource().getOrSetDefault("pack.network-handled", true);
    }

    public int connectWait() {
        reloadResource();

        return getResource().getOrSetDefault("pack.wait", 20);
    }
}
