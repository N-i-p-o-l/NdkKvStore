LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS    := -DHAVE_INTTYPES_H -Wreturn-type -Wswitch
LOCAL_MODULE    := store
LOCAL_SRC_FILES := ru_art_storendk_model_Store.c Store.c

LOCAL_MODULE:= libstore

include $(BUILD_SHARED_LIBRARY)