package test.plugin;

import org.marinemc.events.EventListener;
import org.marinemc.events.EventManager;
import org.marinemc.events.EventPriority;
import org.marinemc.events.standardevents.PreLoginEvent;
import org.marinemc.game.chat.ChatColor;
import org.marinemc.game.scheduler.MarineRunnable;
import org.marinemc.player.IPlayer;
import org.marinemc.player.Player;
import org.marinemc.plugins.Plugin;
import org.marinemc.server.Marine;

import java.util.Arrays;
import java.util.Random;

/**
 * Plugin Test
 *
 * @author Citymonstret
 */
public class PluginMain extends Plugin {

    // epic custom whitelist
    private final String[] blackList = new String[] {
            "Citymonstret", "notch", "Dinnerbone", "xisuma"
    };

    private final String[] adverts = new String[] {
            "Google is our friend",
            "Buy more pizzas",
            "Kittens are cute",
            "Potatoes taste good"
    };

    @Override
    public void onEnable() {
        EventManager.getInstance().addListener(new EventListener<PreLoginEvent>(EventPriority.HIGH) {
            @Override
            public void listen(final PreLoginEvent event) {
                final IPlayer player = event.getPlayer();
                if (!Arrays.asList(blackList).contains(player.getName())) {
                    event.setAllowed(false);
                    event.setMessage(
                            ChatColor.red + ChatColor.bold + "Closed Beta!\n" +
                            ChatColor.yellow + "Apply at: " + ChatColor.blue + "https://google.com"
                    );
                }
            }
        });
        EventManager.getInstance().addListener(new EventListener<PreLoginEvent>(EventPriority.LOW) {
            @Override
            public void listen(final PreLoginEvent event) {
                final IPlayer player = event.getPlayer();
                if (!Arrays.asList(blackList).contains(player.getName())) {
                    event.setAllowed(false);
                    event.setMessage(
                            ChatColor.red + ChatColor.bold + "Potato Closed Beta!\n" +
                                    ChatColor.yellow + "Apply at: " + ChatColor.blue + "https://google.com"
                    );
                }
            }
        });
        Marine.getScheduler().createSyncTask(new MarineRunnable(15 * 20l, -1) {
            final Random random = new Random();
            @Override
            public void run() {
                final String message = adverts[random.nextInt(adverts.length)];
                for (final Player player : Marine.getPlayers()) {
                    player.sendAboveActionbarMessage(message);
                }
            }
        });
    }

}
