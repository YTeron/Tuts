package net.YTeron.weather;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "tuts")
public class WeatherCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // КОМАНДА ДЛЯ ЗАПУСКА ВАШЕЙ КАСТОМНОЙ ПОГОДЫ
        dispatcher.register(Commands.literal("ctormstart")
                .requires(source -> source.hasPermission(2))
                .executes(context -> {
                    ServerLevel level = context.getSource().getLevel();

                    // ЗАПУСКАЕМ ВАШУ ПОГОДУ
                    CustomWeatherManager.startCustomWeather(level);
                    return 1;
                })
        );

        // КОМАНДА ДЛЯ ОСТАНОВКИ ВАШЕЙ ПОГОДЫ
        dispatcher.register(Commands.literal("ctormstop")
                .requires(source -> source.hasPermission(2))
                .executes(context -> {
                    ServerLevel level = context.getSource().getLevel();

                    // ОСТАНАВЛИВАЕМ ВАШУ ПОГОДУ
                    CustomWeatherManager.stopCustomWeather(level);

                    return 1;
                })
        );

        // КОМАНДА ДЛЯ ПРОВЕРКИ СТАТУСА
        dispatcher.register(Commands.literal("ctormstatus")
                .requires(source -> source.hasPermission(0))
                .executes(context -> {
                    boolean isActive = CustomWeatherManager.isWeatherActive();
                    return 1;
                })
        );
    }
}