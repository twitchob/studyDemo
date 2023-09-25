package com.example.encrypt.asn1;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.IssuerAndSerialNumber;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/8 17:55
 */
public class RecipientIdentifier extends ASN1Object implements ASN1Choice {
    private ASN1Encodable id;

    public RecipientIdentifier(IssuerAndSerialNumber id) {
        this.id = id;
    }

    public RecipientIdentifier(ASN1OctetString id) {
        this.id = new DERTaggedObject(false, 0, id);
    }

    public RecipientIdentifier(ASN1Primitive id) {
        this.id = id;
    }

    public static RecipientIdentifier getInstance(Object o) {
        if (o != null && !(o instanceof RecipientIdentifier)) {
            if (o instanceof IssuerAndSerialNumber) {
                return new RecipientIdentifier((IssuerAndSerialNumber)o);
            } else if (o instanceof ASN1OctetString) {
                return new RecipientIdentifier((ASN1OctetString)o);
            } else if (o instanceof ASN1Primitive) {
                return new RecipientIdentifier((ASN1Primitive)o);
            } else {
                throw new IllegalArgumentException("Illegal object in RecipientIdentifier: " + o.getClass().getName());
            }
        } else {
            return (RecipientIdentifier)o;
        }
    }

    public boolean isTagged() {
        return this.id instanceof ASN1TaggedObject;
    }

    public ASN1Encodable getId() {
        return (ASN1Encodable)(this.id instanceof ASN1TaggedObject ? ASN1OctetString.getInstance((ASN1TaggedObject)this.id, false) : IssuerAndSerialNumber.getInstance(this.id));
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        return this.id.toASN1Primitive();
    }
}
