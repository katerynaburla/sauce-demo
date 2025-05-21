package com.swag.labs.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface AppConfig extends Config {

    @Key("base.url")
    String url();

    @Key("password")
    String password();

    @Key("standard.user")
    String standardUser();

    @Key("locked.out.user")
    String lockedOutUser();

    @Key("problem.user")
    String problemUser();

    @Key("error.user")
    String errorUser();

    @Key("visual.user")
    String visualUser();

    @Key("performance.glitch.user")
    String performanceGlitchUser();
}
