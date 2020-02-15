package de.crazypokemondev.tardismod.util.helpers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.IOUtils;

import de.crazypokemondev.tardismod.TardisMod;
import de.crazypokemondev.tardismod.util.schematic.Schematic;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class SchematicHelper {
	private static Map<String, Schematic> schematicCache = new HashMap<>();

	public static Schematic getSchematic(String name) {
		if (schematicCache.containsKey(name)) {
			return schematicCache.get(name);
		} else {
			Schematic schem = loadSchematicFromFile(name);
			schematicCache.put(name, schem);
			return schem;
		}
	}

	private static Schematic loadSchematicFromFile(String name) {
		try {
			DataInputStream stream = new DataInputStream(new FileInputStream(getSchematicFile(name)));
			NBTTagCompound nbt = CompressedStreamTools.readCompressed(stream);
			return Schematic.loadFromNbt(nbt);
		} catch (InvalidFormatException e) {
			TardisMod.LOGGER.error("Couldn't load schematic with name " + name);
			return null;
		} catch (IOException e) {
			TardisMod.LOGGER.error(e.toString());
			e.printStackTrace();
			return null;
		}
	}

	private static File getSchematicFile(String name) {
		File schema = new File(TardisMod.SCHEMA_DIR, name + ".schema");
		if (!schema.exists()) {
			InputStream stream = TardisMod.class.getResourceAsStream("/assets/tardismod/schema/" + name + ".schema");
			try {
				Files.copy(stream, schema.toPath(), StandardCopyOption.REPLACE_EXISTING);
				IOUtils.closeQuietly(stream);
			} catch (IOException e) {
				TardisMod.LOGGER.error(e.toString());
				e.printStackTrace();
			}
		}
		return schema;
	}
}
