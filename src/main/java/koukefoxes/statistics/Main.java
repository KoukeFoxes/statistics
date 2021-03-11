package koukefoxes.statistics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.lang.System.out;
public final class Main extends JavaPlugin {

    int TimerupdateDelay = 10;
    int index = 1;
    String scoreboardtitle = "";
    HashMap<String, Integer> StatisticMaps = new HashMap<>();


    private void ScoreBoard(HashMap<String, Integer> map, String name, Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        final Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.BOLD + name);

        Map<Object, Object> sortedMap =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        int i = 0;
        for (Object key : sortedMap.keySet()) {
            int value = (Integer) sortedMap.get((String) key);
//            Score score = objective.getScore(key.toString() +
//                    ChatColor.GRAY +" - " + ChatColor.GOLD + value);
            Score score = objective.getScore(key.toString());
//            score.setScore(i);
            score.setScore(value);
            i++;
        }

        Score score14 = objective.getScore("");
        score14.setScore(-1);

//        Score scoreEnd = objective.getScore(ChatColor.GRAY + "每"+ (TimerupdateDelayms/1000) +"秒更新");
//        scoreEnd.setScore(-2);
        p.setScoreboard(board);
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendRawMessage("Plugin is Enable..");

        Bukkit.getScheduler().runTaskTimer(this, () -> {

            setStatisticMaps();

            out.print("player list:　");
            for (Player player : Bukkit.getOnlinePlayers()) {
                ScoreBoard(StatisticMaps,scoreboardtitle,player);
                out.println(player.getDisplayName());
//                player.getStatistic(Statistic.BREAK_ITEM, Material.RED_BED);
                out.println("-----\n" + player.getStatistic(Statistic.BREAK_ITEM, Material.IRON_DOOR) + "\n-----");
            }

        }, 0, TimerupdateDelay);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
//        ScoreBoard(StatisticMaps,scoreboardtitle,e.getPlayer());
    }

    int temp = 0;

    private void setStatisticMaps(){

        StatisticMaps.clear();
        temp++;
        if (temp % 600 == 0) {
            index++; //600 = 60s
            temp%=600;
        }
        index %= 41;
//        index = 5;
        out.print("index: "+index);
        out.print("temp: "+temp);
        for (Player PLAYER : Bukkit.getOnlinePlayers()) {
            switch (index){
                case 1:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.ANIMALS_BRED));
                    scoreboardtitle = "繁殖動物榜";
                    break;
                case 2:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.AVIATE_ONE_CM));
                    scoreboardtitle = "AVIATE_ONE_CM";
                    break;
                case 3:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.BANNER_CLEANED));
                    scoreboardtitle = "BANNER_CLEANED";
                    break;
                case 4:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.BEACON_INTERACTION));
                    scoreboardtitle = "烽火檯互動榜";
                    break;
                case 5:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.BELL_RING));
                    scoreboardtitle = "搖鐘次數榜";
                    break;
                case 6:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.BOAT_ONE_CM));
                    scoreboardtitle = "行船距離榜";
                    break;
                case 7:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.BREWINGSTAND_INTERACTION));
                    scoreboardtitle = "釀造檯使用榜";
                    break;
                case 8:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.CAKE_SLICES_EATEN));
                    scoreboardtitle = "吃蛋糕榜";
                    break;
                case 9:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.CAULDRON_FILLED));
                    scoreboardtitle = "裝填鍋釜次數榜";
                    break;
                case 10:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.CAULDRON_USED));
                    scoreboardtitle = "使用鍋釜次數榜";
                    break;
                case 11:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.CHEST_OPENED));
                    scoreboardtitle = "開箱次數榜";
                    break;
                case 12:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.CLEAN_SHULKER_BOX));
                    scoreboardtitle = "CLEAN_SHULKER_BOX";
                    break;
                case 13:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.CLIMB_ONE_CM));
                    scoreboardtitle = "攀爬排行榜";
                    break;
                case 14:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.CRAFTING_TABLE_INTERACTION));
                    scoreboardtitle = "合成檯使用排行榜";
                    break;
                case 15:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.CROUCH_ONE_CM));
                    scoreboardtitle = "CROUCH_ONE_CM";
                    break;
                case 16:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.DAMAGE_ABSORBED));
                    scoreboardtitle = "傷害吸收排榜";
                    break;
                case 17:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.DAMAGE_BLOCKED_BY_SHIELD));
                    scoreboardtitle = "盾牌傷害抵擋榜";
                    break;
                case 18:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.DAMAGE_DEALT));
                    scoreboardtitle = "DAMAGE_DEALT";
                    break;
                case 19:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.DAMAGE_DEALT_ABSORBED));
                    scoreboardtitle = "DAMAGE_DEALT_ABSORBED";
                    break;
                case 20:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.DAMAGE_DEALT_RESISTED));
                    scoreboardtitle = "DAMAGE_DEALT_RESISTED";
                    break;
                case 21:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.DAMAGE_RESISTED));
                    scoreboardtitle = "DAMAGE_RESISTED";
                    break;
                case 22:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.DAMAGE_TAKEN));
                    scoreboardtitle = "DAMAGE_TAKEN";
                    break;
                case 23:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.DEATHS));
                    scoreboardtitle = "死亡排行榜";
                    break;
                case 24:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.DISPENSER_INSPECTED));
                    scoreboardtitle = "DISPENSER_INSPECTED";
                    break;
                case 25:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.DROP_COUNT));
                    scoreboardtitle = "DROP_COUNT";
                    break;
                case 26:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.DROPPER_INSPECTED));
                    scoreboardtitle = "DROPPER_INSPECTED";
                    break;
                case 27:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.ENDERCHEST_OPENED));
                    scoreboardtitle = "終界箱開啟排行榜";
                    break;
                case 28:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.FALL_ONE_CM));
                    scoreboardtitle = "下墜距離榜";
                    break;
                case 29:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.FISH_CAUGHT));
                    scoreboardtitle = "釣魚排行榜";
                    break;
                case 30:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.FLOWER_POTTED));
                    scoreboardtitle = "盆栽種花排行榜";
                    break;
                case 31:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.FLY_ONE_CM));
                    scoreboardtitle = "飛行距離榜";
                    break;
                case 32:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.FURNACE_INTERACTION));
                    scoreboardtitle = "熔爐使用排行榜";
                    break;
                case 33:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.HOPPER_INSPECTED));
                    scoreboardtitle = "HOPPER_INSPECTED";
                    break;
                case 34:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.HORSE_ONE_CM));
                    scoreboardtitle = "騎馬距離榜";
                    break;
                case 35:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.INTERACT_WITH_ANVIL));
                    scoreboardtitle = "鐵砧使用排行榜";
                    break;
                case 36:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.INTERACT_WITH_BLAST_FURNACE));
                    scoreboardtitle = "高爐使用榜";
                    break;
                case 37:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.INTERACT_WITH_CAMPFIRE));
                    scoreboardtitle = "營火互動榜";
                    break;
                case 38:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.INTERACT_WITH_CARTOGRAPHY_TABLE));
                    scoreboardtitle = "製圖桌互動榜";
                    break;
                case 39:
                    StatisticMaps.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.INTERACT_WITH_GRINDSTONE));
                    scoreboardtitle = "沙輪使用次數";
                    break;
                case 40:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.INTERACT_WITH_LECTERN));
                    scoreboardtitle = "講檯互動次數";
                    break;
                case 41:
                    StatisticMaps.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.BREAK_ITEM, Objects.requireNonNull(Material.matchMaterial("TNT"))));
//                    out.print("-----\n" + PLAYER.getStatistic(Statistic.BREAK_ITEM, Material.IRON_DOOR) + "\n-----");
                    scoreboardtitle = "挖掘排行榜";
                    break;
            }

//                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + " " + Bukkit.getOnlinePlayers());
//                    if (ConfigManager.get().getBoolean("BREAK_ITEM")) BREAK_ITEM.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.BREAK_ITEM));
//                    if (ConfigManager.get().getBoolean("CRAFT_ITEM")) CRAFT_ITEM.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.CRAFT_ITEM));
//                    if (ConfigManager.get().getBoolean("DROP")) DROP.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.DROP));
//                    if (ConfigManager.get().getBoolean("ENTITY_KILLED_BY")) ENTITY_KILLED_BY.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.ENTITY_KILLED_BY));

/**
        INTERACT_WITH_LOOM.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.INTERACT_WITH_LOOM));
        INTERACT_WITH_SMITHING_TABLE.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.INTERACT_WITH_SMITHING_TABLE));
        INTERACT_WITH_SMOKER.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.INTERACT_WITH_SMOKER));
        INTERACT_WITH_STONECUTTER.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.INTERACT_WITH_STONECUTTER));
        ITEM_ENCHANTED.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.ITEM_ENCHANTED));

        JUMP.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.JUMP));
//                    if (ConfigManager.get().getBoolean("KILL_ENTITY")) KILL_ENTITY.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.KILL_ENTITY));
        LEAVE_GAME.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.LEAVE_GAME));
//                    if (ConfigManager.get().getBoolean("MINE_BLOCK")) MINE_BLOCK.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.MINE_BLOCK));
        MINECART_ONE_CM.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.MINECART_ONE_CM));
        MOB_KILLS.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.MOB_KILLS));
        NOTEBLOCK_PLAYED.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.NOTEBLOCK_PLAYED));
        NOTEBLOCK_TUNED.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.NOTEBLOCK_TUNED));
        OPEN_BARREL.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.OPEN_BARREL));
//                    if (ConfigManager.get().getBoolean("PICKUP")) PICKUP.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.PICKUP));

        PIG_ONE_CM.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.PIG_ONE_CM));
        PLAY_ONE_MINUTE.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.PLAY_ONE_MINUTE));
        PLAYER_KILLS.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.PLAYER_KILLS));
        RAID_TRIGGER.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.RAID_TRIGGER));
        RAID_WIN.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.RAID_WIN));
        RECORD_PLAYED.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.RECORD_PLAYED));
        SHULKER_BOX_OPENED.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.SHULKER_BOX_OPENED));
        SLEEP_IN_BED.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.SLEEP_IN_BED));
        SNEAK_TIME.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.SNEAK_TIME));
        SPRINT_ONE_CM.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.SPRINT_ONE_CM));
        STRIDER_ONE_CM.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.STRIDER_ONE_CM));
        SWIM_ONE_CM.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.SWIM_ONE_CM));

        TALKED_TO_VILLAGER.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.TALKED_TO_VILLAGER));
        TARGET_HIT.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.TARGET_HIT));
        TIME_SINCE_DEATH.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.TIME_SINCE_DEATH));
        TIME_SINCE_REST.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.TIME_SINCE_REST));
        TRADED_WITH_VILLAGER.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.TRADED_WITH_VILLAGER));
        TRAPPED_CHEST_TRIGGERED.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.TRAPPED_CHEST_TRIGGERED));
//                    if (ConfigManager.get().getBoolean("USE_ITEM")) USE_ITEM.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.USE_ITEM));
        WALK_ON_WATER_ONE_CM.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.WALK_ON_WATER_ONE_CM));
        WALK_ONE_CM.put(PLAYER.getDisplayName(), PLAYER.getStatistic(Statistic.WALK_ONE_CM));
        WALK_UNDER_WATER_ONE_CM.put(PLAYER.getDisplayName(),PLAYER.getStatistic(Statistic.WALK_UNDER_WATER_ONE_CM));
**/
        }
    }
}
