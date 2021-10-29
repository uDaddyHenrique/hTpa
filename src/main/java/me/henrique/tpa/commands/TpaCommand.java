package me.henrique.tpa.commands;

import me.henrique.tpa.Tpa;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TpaCommand extends Commands {

    public TpaCommand() {
        super("tpa");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("§eTpa - Ajuda");
                p.sendMessage("");
                p.sendMessage("§7/tpa [nick] - Enviar uma solicitação de tpa.");
                p.sendMessage("§7/tpa aceitar [nick] - Aceitar uma solicitação de tpa.");
                p.sendMessage("§7/tpa recusar [nick] - Recusar uma solicitação de tpa.");
                p.sendMessage("");
                return false;
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage("§cEste jogador está offline ou não existe.");
                } else {
                    if (target == p) {
                        p.sendMessage("§cVocê não pode enviar solicitações de tpa a sí mesmo.");
                    } else {
                        p.sendMessage("§aSolicitação de tpa enviada para " + target.getName() + " com sucesso.");
                        target.sendMessage("§aVocê recebeu uma solicitação de tpa de " + p.getName() + " você tem 60 segundos para aceitar.");
                        Tpa.tpa.put(p.getName(), target.getName());
                        Bukkit.getScheduler().runTaskTimer(Tpa.getInstance(), new BukkitRunnable() {
                            @Override
                            public void run() {
                                Tpa.tpa.remove(p.getName());
                            }
                        }, 20 * 60, 20 * 60);
                    }
                }
            }
            if (args[0].equalsIgnoreCase("aceitar")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (!Tpa.tpa.containsKey(target.getName())) {
                    p.sendMessage("§cVocê não possuí solicitações pendentes deste jogador.");
                } else {
                    if (target == null) {
                        p.sendMessage("§cEste jogador está offline ou não existe.");
                    } else {
                        if (target == p) {
                            p.sendMessage("§cVocê não pode enviar solicitações de tpa a sí mesmo.");
                        } else {
                            p.sendMessage("§aVocê aceitou a solicitação de tpa de " + target.getName());
                            target.sendMessage("§a" + p.getName() + " aceitou sua solicitação de tpa, você será teletransportado em 3s");
                            Tpa.tpa.remove(target.getName());
                            Bukkit.getScheduler().runTaskLater(Tpa.getInstance(), () -> {
                                target.teleport(p);
                            }, 20 * 3);
                        }
                    }
                }
            }
            if(args[0].equalsIgnoreCase("recusar")){
                Player target = Bukkit.getPlayer(args[1]);
                if (!Tpa.tpa.containsKey(target.getName())) {
                    p.sendMessage("§cVocê não possuí solicitações pendentes deste jogador.");
                } else {
                    if (target == null) {
                        p.sendMessage("§cEste jogador está offline ou não existe.");
                    } else {
                        if (target == p) {
                            p.sendMessage("§cVocê não pode enviar solicitações de tpa a sí mesmo.");
                            } else {
                            p.sendMessage("§aVocê recusou a solicitação de tpa do " + target.getName());
                            target.sendMessage("§a" + p.getName() + " recusou sua solicitação de tpa.");
                            Tpa.tpa.remove(target.getName());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
