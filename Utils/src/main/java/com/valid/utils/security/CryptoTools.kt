package com.valid.utils.security

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.PublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.X509EncodedKeySpec
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher

object CryptoTools {

    private const val ALGORITHM = "RSA/ECB/PKCS1Padding"
    private const val KEY_ALGORITHM = "RSA"

    fun encrypt(stringPublicKey: String, message: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKeyFromString(stringPublicKey))
        val finalByteArray = cipher.doFinal(message.toByteArray())
        return Base64.encodeToString(finalByteArray, Base64.DEFAULT).replace("\n", "")
    }

    private fun getPublicKeyFromString(base64PublicKey: String): PublicKey? {
        var publicKey: PublicKey? = null
        try {
            val newStringPublicKey = base64PublicKey.replace("\n", "")
            val keySpec = X509EncodedKeySpec(Base64.decode(newStringPublicKey.toByteArray(), Base64.DEFAULT))
            val keyFactory: KeyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
            publicKey = keyFactory.generatePublic(keySpec)
            return publicKey
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
        }
        return publicKey
    }


    fun buildPasswordDigest(tranKey: String, nonce: String, seed: String): String {
        val sha256: MessageDigest
        var passwordDigest: String = ""
        try {
            sha256 = MessageDigest.getInstance("SHA-256")
            sha256.update(nonce.toByteArray(charset("UTF-8")))
            sha256.update(seed.toByteArray(charset("UTF-8")))
            sha256.update(tranKey.toByteArray(charset("UTF-8")))
            passwordDigest = Base64.encodeToString(sha256.digest(), Base64.DEFAULT)
            sha256.reset()
        } catch (e: NoSuchAlgorithmException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return passwordDigest
    }

    fun getPlainNonce(): String {
        return UUID.randomUUID().toString()
    }

    fun buildNonce(nonce: String): String {
        return Base64.encodeToString(nonce.toByteArray(), Base64.DEFAULT)
    }

    fun buildSeed(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT)
        val datePart = sdf.format(Date())
        val arrayDate = datePart.split(" ")
        return arrayDate[0] + "T" + arrayDate[1] + "-05:00"
    }
}