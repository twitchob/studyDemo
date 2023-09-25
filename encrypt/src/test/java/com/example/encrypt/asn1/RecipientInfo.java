package com.example.encrypt.asn1;

import org.bouncycastle.asn1.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/8 17:54
 */
public class RecipientInfo extends ASN1Object implements ASN1Choice {

    ASN1Encodable info;

    public RecipientInfo(KeyTransRecipientInfo info) {
        this.info = info;
    }


    public RecipientInfo(ASN1Primitive info) {
        this.info = info;
    }


    public boolean isTagged() {
        return this.info instanceof ASN1TaggedObject;
    }


    @Override
    public ASN1Primitive toASN1Primitive() {
        return this.info.toASN1Primitive();
    }
}
