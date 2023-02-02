package com.example.testhsmutil;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public abstract interface HsmLib extends Library {
  public static final HsmLib INSTANCE = (HsmLib)Native.load("tsr-rsps10", HsmLib.class);
  
  public abstract int SDF_OpenDevice(PointerByReference paramPointer);
  
  public abstract int SDF_CloseDevice(Pointer paramPointer);
  
  public abstract int SDF_OpenSession(Pointer paramPointer, PointerByReference sessionPointer);
  
  public abstract int SDF_CloseSession(Pointer paramPointer);
  
  public abstract int SDF_GenerateRandom(Pointer paramPointer, int length, byte[] outRandom);
  
  public abstract int SDF_Encrypt(Pointer paramPointer, Pointer keyPointer, int algId, byte[] ivByReference, byte[] plaintext, int plaintextLength, byte[] outCiphertext, IntByReference outCiphertextLength);
  
  public abstract int SDF_Decrypt(Pointer paramPointer, Pointer keyPointer, int algId, byte[] ivByReference, byte[] ciphertext, int ciphertextLength, byte[] outPlaintext, IntByReference outPlaintextLength);
  
  public abstract int SDF_ImportKey(Pointer paramPointer, byte[] key, int keyLength, PointerByReference keyPointer);

  public abstract int SDF_DestroyKey(Pointer paramPointer, Pointer keyPointer);
  
}
