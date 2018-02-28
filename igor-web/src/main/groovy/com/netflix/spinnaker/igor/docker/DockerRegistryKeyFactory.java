package com.netflix.spinnaker.igor.docker;

import com.google.common.base.Splitter;
import com.google.common.base.VerifyException;

import static com.google.common.base.Verify.verify;

import java.util.List;

public class DockerRegistryKeyFactory {
    private final static String ID = "dockerRegistry";

    DockerRegistryV1Key parseV1Key(String keyStr, boolean includeRepository) throws DockerRegistryKeyFormatException {
        List<String> splits = Splitter.on(',').splitToList(keyStr);
        try {
            String prefix = splits.get(0);
            verify(prefix.equals(prefix()), "Expected prefix '%s', found '%s'", prefix(), prefix);

            String id = splits.get(1);
            verify(ID.equals(id), "Expected ID '%s', found '%s'", ID, id);

            String account = splits.get(2);
            verify(!account.isEmpty(), "Empty account string");

            String registry = splits.get(3);
            verify(!registry.isEmpty(), "Empty registry string");

            // TODO: repository
            if (includeRepository) {
                throw new UnsupportedOperationException("not implemented yet");
            }

            String tag = splits.get(splits.size() - 1);
            verify(!tag.isEmpty(), "Empty registry string");

            return new DockerRegistryV1Key(prefix, id, account, registry, null, tag);
        } catch(IndexOutOfBoundsException | VerifyException e) {
            throw new DockerRegistryKeyFormatException(String.format("Could not parse '%s' as a v1 key", keyStr), e);
        }

    }

    DockerRegistryV1Key parseV2Key(String keyStr) throws DockerRegistryKeyFormatException {
        throw new UnsupportedOperationException("not implemented yet");
    }

    DockerRegistryV2Key convert(DockerRegistryV1Key oldKey) {
        return new DockerRegistryV2Key(
            oldKey.getPrefix(),
            oldKey.getId(),
            oldKey.getAccount(),
            oldKey.getRegistry(),
            oldKey.getTag()
        );
    }

    private String prefix() {
        return igorConfigurationProperties.getSpinnaker().getJedis().getPrefix();
    }
}
