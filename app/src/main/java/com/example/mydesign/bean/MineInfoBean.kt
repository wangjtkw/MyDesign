package com.example.mydesign.bean

import android.os.Parcel
import android.os.Parcelable

data class MineInfoBean(
    val img: String?,
    val name: String?,
    val sex: String?,
    val birthday: String?,
    val role: String?,
    val phoneNum: String?,
    val weChat: String?,
    val qq: String?,
    val educationExperience: EducationExperience?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(EducationExperience::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(name)
        parcel.writeString(sex)
        parcel.writeString(birthday)
        parcel.writeString(role)
        parcel.writeString(phoneNum)
        parcel.writeString(weChat)
        parcel.writeString(qq)
        parcel.writeParcelable(educationExperience, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MineInfoBean> {
        override fun createFromParcel(parcel: Parcel): MineInfoBean {
            return MineInfoBean(parcel)
        }

        override fun newArray(size: Int): Array<MineInfoBean?> {
            return arrayOfNulls(size)
        }
    }
}

data class EducationExperience(
    val education: String?,
    val school: String?,
    val enterDate: String?,
    val major: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(education)
        parcel.writeString(school)
        parcel.writeString(enterDate)
        parcel.writeString(major)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EducationExperience> {
        override fun createFromParcel(parcel: Parcel): EducationExperience {
            return EducationExperience(parcel)
        }

        override fun newArray(size: Int): Array<EducationExperience?> {
            return arrayOfNulls(size)
        }
    }
}