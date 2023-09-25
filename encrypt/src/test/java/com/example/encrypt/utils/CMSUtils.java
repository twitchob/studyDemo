package com.example.encrypt.utils;

import com.example.encrypt.asn1.SignerIdentifier;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.TBSCertificateStructure;
import org.bouncycastle.asn1.x509.X509CertificateStructure;

import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/11 10:44
 */
public class CMSUtils {

    public static List getCertificatesFromStore(CertStore certStore)  {

        List certs = new ArrayList();
        Iterator it = null;
        try {
            it = certStore.getCertificates((CertSelector) null).iterator();
        } catch (CertStoreException e) {
            throw new RuntimeException(e);
        }
        while (it.hasNext()) {
            X509Certificate c = (X509Certificate) it.next();
            try {
                certs.add(X509CertificateStructure.getInstance(ASN1Primitive.fromByteArray(c.getEncoded())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (CertificateEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return certs;
    }

    public static List getCRLsFromStore(CertStore certStore)  {
        List crls = new ArrayList();
        Iterator it = null;
        try {
            it = certStore.getCRLs((CRLSelector) null).iterator();
        } catch (CertStoreException e) {
            throw new RuntimeException(e);
        }

        while (it.hasNext()) {
            X509CRL c = (X509CRL) it.next();
            try {
                crls.add(CertificateList.getInstance(ASN1Primitive.fromByteArray(c.getEncoded())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (CRLException e) {
                throw new RuntimeException(e);
            }
        }

        return crls;
    }

    public static Provider getProvider(String providerName) throws NoSuchProviderException {
        if (providerName != null) {
            Provider prov = Security.getProvider(providerName);
            if (prov != null) {
                return prov;
            } else {
                throw new NoSuchProviderException("provider " + providerName + " not found.");
            }
        } else {
            return null;
        }
    }

    public static ASN1Set createBerSetFromList(List derObjects) {
        ASN1EncodableVector v = new ASN1EncodableVector();
        Iterator it = derObjects.iterator();

        while(it.hasNext()) {
            v.add((ASN1Encodable)it.next());
        }

        return new BERSet(v);
    }
    public static SignerIdentifier getSignerIdentifier(X509Certificate cert) {
        TBSCertificateStructure tbs;
        try {
            tbs = CMSUtils.getTBSCertificateStructure(cert);
        } catch (CertificateEncodingException var3) {
            throw new IllegalArgumentException("can't extract TBS structure from this cert");
        }

        IssuerAndSerialNumber encSid = new IssuerAndSerialNumber(tbs.getIssuer(), tbs.getSerialNumber().getValue());
        return new SignerIdentifier(encSid);
    }

    static TBSCertificateStructure getTBSCertificateStructure(X509Certificate cert) throws CertificateEncodingException {
        try {
            return TBSCertificateStructure.getInstance(ASN1Primitive.fromByteArray(cert.getTBSCertificate()));
        } catch (IOException var2) {
            throw new CertificateEncodingException(var2.toString());
        }
    }

}
