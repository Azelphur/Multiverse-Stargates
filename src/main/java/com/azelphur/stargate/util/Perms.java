package com.azelphur.stargate.util;

import com.dumptruckman.minecraft.pluginbase.permission.Perm;

/**
 * Created with IntelliJ IDEA.
 * User: azelphur
 * Date: 06/04/12
 * Time: 05:07
 * To change this template use File | Settings | File Templates.
 */


public class Perms {
    public static final Perm CMD_DIAL = new Perm.Builder("cmd.dial").commandPermission().usePluginName().build();
    public static final Perm CMD_CREATE = new Perm.Builder("cmd.create").commandPermission().usePluginName().build();
}