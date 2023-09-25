package com.example.encrypt.asn1;

import com.example.encrypt.utils.RecipientInfoGenerator;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.pkcs.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.TBSCertificateStructure;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/8 17:59
 */
public class KeyTransRecipientInfoGenerator implements RecipientInfoGenerator {
    private TBSCertificateStructure recipientTBSCert;
    private PublicKey recipientPublicKey;
    private ASN1OctetString subjectKeyIdentifier;
    private SubjectPublicKeyInfo info;

    public KeyTransRecipientInfoGenerator() {
    }

    public void setRecipientCert(X509Certificate recipientCert) {
        try {
            this.recipientTBSCert = getTBSCertificateStructure(recipientCert);
        } catch (CertificateEncodingException var3) {
            throw new IllegalArgumentException("can't extract TBS structure from this cert");
        }

        this.recipientPublicKey = recipientCert.getPublicKey();
        this.info = this.recipientTBSCert.getSubjectPublicKeyInfo();
    }

    TBSCertificateStructure getTBSCertificateStructure(X509Certificate cert) throws CertificateEncodingException {
        try {
            return TBSCertificateStructure.getInstance(ASN1Primitive.fromByteArray(cert.getTBSCertificate()));
        } catch (IOException var2) {
            throw new CertificateEncodingException(var2.toString());
        }
    }

    void setRecipientPublicKey(PublicKey recipientPublicKey) {
        this.recipientPublicKey = recipientPublicKey;

        try {
            this.info = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(recipientPublicKey.getEncoded()));
        } catch (IOException var3) {
            throw new IllegalArgumentException("can't extract key algorithm from this key");
        }
    }

    void setSubjectKeyIdentifier(ASN1OctetString subjectKeyIdentifier) {
        this.subjectKeyIdentifier = subjectKeyIdentifier;
    }

    public RecipientInfo generate(SecretKey key, SecureRandom random, Provider prov) throws GeneralSecurityException {
        AlgorithmIdentifier keyEncAlg = this.info.getAlgorithm();
        Cipher keyCipher = Cipher.getInstance(keyEncAlg.getAlgorithm().getId(), prov);

        DEROctetString encKey;
        try {
            keyCipher.init(3, this.recipientPublicKey, random);
            encKey = new DEROctetString(keyCipher.wrap(key));
        } catch (GeneralSecurityException var9) {
            keyCipher.init(1, this.recipientPublicKey, random);
            encKey = new DEROctetString(keyCipher.doFinal(key.getEncoded()));
        } catch (IllegalStateException var10) {
            keyCipher.init(1, this.recipientPublicKey, random);
            encKey = new DEROctetString(keyCipher.doFinal(key.getEncoded()));
        } catch (UnsupportedOperationException var11) {
            keyCipher.init(1, this.recipientPublicKey, random);
            encKey = new DEROctetString(keyCipher.doFinal(key.getEncoded()));
        }

        RecipientIdentifier recipId;
        if (this.recipientTBSCert != null) {
            IssuerAndSerialNumber issuerAndSerial = new IssuerAndSerialNumber(this.recipientTBSCert.getIssuer(), this.recipientTBSCert.getSerialNumber().getValue());
            recipId = new RecipientIdentifier(issuerAndSerial);
        } else {
            recipId = new RecipientIdentifier(this.subjectKeyIdentifier);
        }

        return new RecipientInfo(new KeyTransRecipientInfo(recipId, keyEncAlg, encKey));
    }

    public String getCipherOID() {
        return GBObjectIdentifiers.sm2.getId();
    }
}
