package com.test.concord.service;

import java.util.Optional;

public interface EncryptionService {

    Optional<String> encrypt(String inputData);

    Optional<String> decrypt(String inputData);
}
