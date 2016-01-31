import org.jnbt.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mac on 12/8/2015.
 */
public class VillageGenerator {
    private final int ACX = -2464;
    private final int ACY = 1782;
    private int ACZ = 0;
    private final int CX = -112;
    private final int CY = 81;
    private int CZ = 239;
    private final int RADIUS = 32;
    private final int TICK = 31586978;

    public static void main(String[] args) {
        VillageGenerator generator = new VillageGenerator();
        generator.run();
    }

    public void run() {
        System.out.println("Starting...");
        CompoundTag tag = getData();
        try {
            System.out.println("Writing to the file...");
            FileOutputStream file = new FileOutputStream(new File("villages.dat"));
            NBTOutputStream out = new NBTOutputStream(file);
            out.writeTag(tag);
            out.close();
            file.close();
            System.out.println("Done.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CompoundTag getData() {
        IntTag tag = new IntTag("Tick", TICK);
        ListTag villages = new ListTag("Villages", CompoundTag.class, getVillages());
        HashMap<String, Tag> tags = new HashMap<>();
        tags.put("Tick", tag);
        tags.put("Villages", villages);
        HashMap<String, Tag> stuff = new HashMap<>();
        stuff.put("", new CompoundTag("data", tags));
        return new CompoundTag("", stuff);
    }

    private List<Tag> getVillages() {
        ArrayList<Tag> villages = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            villages.add(getVillage());
        }
        return villages;
    }

    private CompoundTag getVillage() {
        System.out.println("Adding a village...");
        HashMap<String, Tag> tags = new HashMap<>();
        tags.put("ACX", new IntTag("ACX", ACX));
        tags.put("ACY", new IntTag("ACY", ACY));
        tags.put("ACZ", new IntTag("ACZ", CZ * 22));
        tags.put("CX", new IntTag("CX", CX));
        tags.put("CY", new IntTag("CY", CY));
        tags.put("CZ", new IntTag("CZ", CZ));
        tags.put("Golems", new IntTag("Golems", 0));
        tags.put("MTick", new IntTag("MTick", 0));
        tags.put("PopSize", new IntTag("PopSize", 0));
        tags.put("Radius", new IntTag("Radius", 32));
        tags.put("Stable", new IntTag("Stable", 31460818));
        tags.put("Tick", new IntTag("Tick", TICK));
        tags.put("Doors", new ListTag("Doors", CompoundTag.class, getDoors()));
        tags.put("Players", new ListTag("Players", CompoundTag.class, new ArrayList<>()));
        CZ++;
        System.out.println("Finished.");
        return new CompoundTag("", tags);
    }

    private List<Tag> getDoors() {
        ArrayList<Tag> doors = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            doors.add(getDoor(-132 + i));
        }
        for (int i = 0; i < 11; i++) {
            doors.add(getDoor(-102 + i));
        }
        return doors;
    }

    private CompoundTag getDoor(int x) {
        HashMap<String, Tag> tags = new HashMap<>();
        tags.put("ADX", new IntTag("ADX", -2));
        tags.put("ADZ", new IntTag("ADZ", 0));
        tags.put("TS", new IntTag("TS", TICK));
        tags.put("X", new IntTag("X", x));
        tags.put("Y", new IntTag("Y", 81));
        tags.put("Z", new IntTag("Z", CZ));
        System.out.println("Door added.");
        return new CompoundTag("", tags);
    }
}
