package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataConversionException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {
    /**
     * This is a static-methods-only (utility) class which should not be instantiated.
     * Note that this is not a singleton class given that not even a single instance is allowed.
     *
     * Throws an {@link InstantiationError} when accessed to prevent instantiation
     * via new, clone(), reflection and serialization.
     */
    private ConfigUtil() {
        // Prevents instantiation via new, clone(), reflection and serialization.
        throw new InstantiationError(
                "This is a static-methods-only (utility) class which should not be instantiated.");
    }

    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
