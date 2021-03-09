package com.valid.domain.utils

import com.valid.businessmodels.request.Auth
import com.valid.domain.R
import com.valid.utils.ViewManager
import com.valid.utils.security.CryptoTools

object AuthenticationManager {
    fun buildAuthentication(nonce : String): Auth {
        val seed = CryptoTools.buildSeed()
        val cryptNone = CryptoTools.buildNonce(nonce)
        return Auth(
            login = ViewManager.getInstance.getString(R.string.login_service),
            tranKey = CryptoTools.buildPasswordDigest(
                tranKey = ViewManager.getInstance.getString(R.string.tranKey_service),
                seed = seed,
                nonce = nonce
            ),
            nonce = cryptNone,
            seed = seed
        )
    }
}