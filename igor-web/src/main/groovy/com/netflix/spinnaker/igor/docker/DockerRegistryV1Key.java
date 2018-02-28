package com.netflix.spinnaker.igor.docker;

import javax.annotation.Nullable;

public class DockerRegistryV1Key {
    private final String prefix;
    private final String id;
    private final String account;
    private final String registry;

    @Nullable
    private final String repository;
    private final String tag;

    public DockerRegistryV1Key(String prefix, String id, String account, String registry, String repository, String tag) {
        this.prefix = prefix;
        this.id = id;
        this.account = account;
        this.registry = registry;
        this.repository = repository;
        this.tag = tag;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getRegistry() {
        return registry;
    }

    @Nullable
    public String getRepository() {
        return repository;
    }

    public String getTag() {
        return tag;
    }

    public String toString() {
        return String.format("%s:%s:%s:%s:%s:%s", prefix, id, account, registry, repository, tag);
    }
}
