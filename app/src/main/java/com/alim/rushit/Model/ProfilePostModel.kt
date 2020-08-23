package com.alim.rushit.Model

import android.os.Parcel
import android.os.Parcelable

class ProfilePostModel(): Parcelable {

    var imagelink = ""
    var description = ""

    constructor(parcel: Parcel) : this() {
        description = parcel.readString()!!
        imagelink = parcel.readString()!!
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0!!.writeString(description)
        p0.writeString(imagelink)
    }

    companion object CREATOR : Parcelable.Creator<ProfilePostModel> {
        override fun createFromParcel(parcel: Parcel): ProfilePostModel {
            return ProfilePostModel(parcel)
        }

        override fun newArray(size: Int): Array<ProfilePostModel?> {
            return arrayOfNulls(size)
        }
    }
}