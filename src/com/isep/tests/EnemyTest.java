package com.isep.tests;

import com.isep.rpg.Basic;
import com.isep.rpg.Boss;
import junit.framework.TestCase;
import org.junit.jupiter.api.*;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

public class EnemyTest extends TestCase {

    private static Instant startedAt;

    @BeforeAll
    static public void initStartingTime() {
        startedAt = Instant.now();
    }

    @AfterAll
    static public void showTestDuration() {
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @Test
    public void testGetName(){
        Basic basicEnemy = new Basic("basicEnemyTest");
        Boss bossEnemy = new Boss("bossEnemyTest");
        Assertions.assertEquals("basicEnemyTest", basicEnemy.getName());
        Assertions.assertEquals("bossEnemyTest", bossEnemy.getName());
    }

    @Test
    public void testGetLifePoints(){
        Basic basicEnemy = new Basic("basicEnemyTest");
        Boss bossEnemy = new Boss("bossEnemyTest");
        Assertions.assertEquals(50, basicEnemy.getLifePoints());
        Assertions.assertEquals(80, bossEnemy.getLifePoints());
    }

    @Test
    public void testGetWeaponDamages(){
        Basic basicEnemy = new Basic("basicEnemyTest");
        Boss bossEnemy = new Boss("bossEnemyTest");
        Assertions.assertEquals(5, basicEnemy.getWeaponDamages());
        Assertions.assertEquals(10, bossEnemy.getWeaponDamages());
    }

    @Test
    public void testDeleteLifePoints(){
        Basic basicEnemy = new Basic("basicEnemyTest");
        Boss bossEnemy = new Boss("bossEnemyTest");
        basicEnemy .deleteLifePoints(10);
        bossEnemy.deleteLifePoints(10);
        Assertions.assertEquals(40, basicEnemy.getLifePoints());
        Assertions.assertEquals(70, bossEnemy.getLifePoints());
    }

}
