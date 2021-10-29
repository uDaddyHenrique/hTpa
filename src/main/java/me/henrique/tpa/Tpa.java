package me.henrique.tpa;

import me.henrique.tpa.commands.Commands;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Author: DaddyHenrique
 * Codigo criado em speedcode no canal: https://www.youtube.com/channel/UCHwZic1fauC2rekmp_2yumQ
 * Obs: Se for possível não tire essa anotação, ela não muda em nada do código.
 * Mas se não quiser tudo bem, lembrando que não darei suporte.
 *
 */

public class Tpa extends JavaPlugin {

    private static Tpa instance;
    public static Map<String, String> tpa = new HashMap<>();

    public Tpa(){
        instance = this;
    }

    @Override
    public void onEnable() {
        Commands.makeCommands();
    }

    public static Tpa getInstance(){
        return instance;
    }
}