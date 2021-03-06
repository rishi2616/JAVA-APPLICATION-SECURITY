package com.kingshuk.appsecurity;

import com.kingshuk.appsecurity.hashing.HashUtils;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.UUID;

import static com.kingshuk.appsecurity.hashing.HashUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

class HashUtilsTest {

    @Test
    void testGenerateSalt() {
        byte[] salt = generateSalt();
        assertThat(salt).isNotNull();
        System.out.println(DatatypeConverter.printHexBinary(salt));
    }

    @Test
    void testCreateSHA256Hash() throws Exception {
        byte[] salt = generateSalt();
        String valueToHash = UUID.randomUUID().toString();
        System.out.println("The UUID before hash is: " + valueToHash);
        final byte[] sha256Hash = createSHA256Hash(valueToHash, salt);
        assertThat(sha256Hash).isNotNull();
        System.out.println("The UUID after the hash is: " + DatatypeConverter.printHexBinary(sha256Hash));

        final byte[] sha256Hash2 = createSHA256Hash(valueToHash, salt);
        assertThat(DatatypeConverter.printHexBinary(sha256Hash)).isEqualTo(DatatypeConverter.printHexBinary(sha256Hash2));
    }


    @Test
    void testPassword() throws Exception {
        String password = "Iofdtiger#16";
        final String hashPassword = HashUtils.hashPassword(password);
        System.out.println("\n The hashed password is: " + hashPassword +"\n");
        assertThat(verifyPassword(password, hashPassword)).isTrue();
    }
}