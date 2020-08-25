package com.alim.rushit.Model

import android.os.Parcel
import android.os.Parcelable

class ChatModel(): Parcelable {

    var sentby = 0
    var message = ""

    constructor(parcel: Parcel) : this() {
        sentby = parcel.readInt()
        message = parcel.readString()!!
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0!!.writeInt(sentby)
        p0.writeString(message)
    }

    companion object CREATOR : Parcelable.Creator<ChatModel> {
        override fun createFromParcel(parcel: Parcel): ChatModel {
            return ChatModel(parcel)
        }

        override fun newArray(size: Int): Array<ChatModel?> {
            return arrayOfNulls(size)
        }
    }
}