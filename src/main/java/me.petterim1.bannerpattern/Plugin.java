package me.petterim1.bannerpattern;

import cn.nukkit.block.BlockID;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityBanner;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.ItemDye;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.BannerPattern;

public class Plugin extends PluginBase implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK || e.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem().getId() == 434) {
                if (e.getBlock().getId() == BlockID.STANDING_BANNER || e.getBlock().getId() == BlockID.WALL_BANNER) {
                    BlockEntity be = e.getPlayer().getLevel().getBlockEntity(e.getBlock());
                    if (be instanceof BlockEntityBanner) {
                        BlockEntityBanner banner = (BlockEntityBanner) be;
                        switch (e.getItem().getDamage()) {
                            case 0:
                                banner.addPattern(new BannerPattern(BannerPattern.Type.PATTERN_CREEPER, banner.getDyeColor()));
                                break;
                            case 1:
                                banner.addPattern(new BannerPattern(BannerPattern.Type.PATTERN_SKULL, banner.getDyeColor()));
                                break;
                            case 2:
                                banner.addPattern(new BannerPattern(BannerPattern.Type.PATTERN_FLOWER, banner.getDyeColor()));
                                break;
                            case 3:
                                banner.addPattern(new BannerPattern(BannerPattern.Type.PATTERN_MOJANG, banner.getDyeColor()));
                                break;
                            case 4:
                                banner.addPattern(new BannerPattern(BannerPattern.Type.PATTERN_BRICK, banner.getDyeColor()));
                                break;
                            case 5:
                                banner.addPattern(new BannerPattern(BannerPattern.Type.PATTERN_CURLY_BORDER, banner.getDyeColor()));
                                break;
                            default:
                                return;
                        }
                        PlayerInventory inv = e.getPlayer().getInventory();
                        inv.decreaseCount(inv.getHeldItemIndex());
                        banner.spawnToAll();
                    }
                }
            } else if (e.getItem().getId() == 351) {
                if (e.getBlock().getId() == BlockID.STANDING_BANNER || e.getBlock().getId() == BlockID.WALL_BANNER) {
                    BlockEntity be = e.getPlayer().getLevel().getBlockEntity(e.getBlock());
                    if (be instanceof BlockEntityBanner) {
                        BlockEntityBanner banner = (BlockEntityBanner) be;
                        if (banner.getPatternsSize() > 0) {
                            BannerPattern pattern = new BannerPattern(banner.getPattern(0).getType(), ((ItemDye) e.getItem()).getDyeColor());
                            banner.removePattern(0);
                            banner.addPattern(pattern);
                            banner.spawnToAll();
                            PlayerInventory inv = e.getPlayer().getInventory();
                            inv.decreaseCount(inv.getHeldItemIndex());
                        }
                    }
                }
            }
        }
    }
}
