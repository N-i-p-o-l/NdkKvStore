#include "ru_art_storendk_model_Store.h"
#include "Store.h"
#include <stdint.h>
#include <string.h>


/**
 * Contains the unique store instance in a static variable created
 * when library is loaded.
 */
static Store mStore = { {}, 0 };


JNIEXPORT jint JNICALL Java_ru_art_storendk_model_Store_getInteger
  (JNIEnv* pEnv, jobject pThis, jstring pKey) {
    StoreEntry* lEntry = findEntry(pEnv, &mStore, pKey, NULL);
    if (isEntryValid(pEnv, lEntry, StoreType_Integer)) {
        return lEntry->mValue.mInteger;
    } else {
        return 0;
    }
}

JNIEXPORT void JNICALL Java_ru_art_storendk_model_Store_setInteger
  (JNIEnv* pEnv, jobject pThis, jstring pKey, jint pInteger) {
    StoreEntry* lEntry = allocateEntry(pEnv, &mStore, pKey);
    if (lEntry != NULL) {
        lEntry->mType = StoreType_Integer;
        lEntry->mValue.mInteger = pInteger;
    }
}

JNIEXPORT jstring JNICALL Java_ru_art_storendk_model_Store_getString
  (JNIEnv* pEnv, jobject pThis, jstring pKey) {
    StoreEntry* lEntry = findEntry(pEnv, &mStore, pKey, NULL);
    if (isEntryValid(pEnv, lEntry, StoreType_String)) {
        // Converts a C string into a Java String.
        return (*pEnv)->NewStringUTF(pEnv, lEntry->mValue.mString);
    } else {
        return NULL;
    }
}

JNIEXPORT void JNICALL Java_ru_art_storendk_model_Store_setString
  (JNIEnv* pEnv, jobject pThis, jstring pKey, jstring pString) {
    // Turns the Java string into a temporary C string.
    // GetStringUTFChars() is used here as an example but
    // Here, GetStringUTFChars() to show
    // the way it works. But as what we want is only a copy,
    // GetBooleanArrayRegion() would be be more efficient.
    const char* lStringTmp = (*pEnv)->GetStringUTFChars(pEnv, pString, NULL);
    if (lStringTmp == NULL) {
        return;
    }

    StoreEntry* lEntry = allocateEntry(pEnv, &mStore, pKey);
    if (lEntry != NULL) {
        lEntry->mType = StoreType_String;
        // Copy the temporary C string into its dynamically allocated
        // final location. Then releases the temporary string.
        // Malloc return value should theoretically be checked...
        jsize lStringLength = (*pEnv)->GetStringUTFLength(pEnv, pString);
        lEntry->mValue.mString =
            (char*) malloc(sizeof(char) * (lStringLength + 1));
        strcpy(lEntry->mValue.mString, lStringTmp);
    }
    (*pEnv)->ReleaseStringUTFChars(pEnv, pString, lStringTmp);
}
