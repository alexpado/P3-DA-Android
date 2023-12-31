package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 *
 * @noinspection unused
 */
public class Neighbour {

    /** Identifier */
    private long id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Address */
    private String address;

    /** Phone number */
    private String phoneNumber;

    /** About me */
    private String aboutMe;

    /**
     * Is favorite ?
     */
    private boolean favorite;

    /**
     * Constructor
     *
     * @param id
     *         Identifier
     * @param name
     *         Custom name
     * @param avatarUrl
     *         URL pointing to the avatar
     */
    public Neighbour(long id, String name, String avatarUrl, String address,
                     String phoneNumber, String aboutMe) {

        this.id          = id;
        this.name        = name;
        this.avatarUrl   = avatarUrl;
        this.address     = address.replace("5km", String.format("%skm", this.id));
        this.phoneNumber = phoneNumber;
        this.aboutMe     = aboutMe;
        this.favorite    = false;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAvatarUrl() {

        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {

        this.avatarUrl = avatarUrl;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public String getAboutMe() {

        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {

        this.aboutMe = aboutMe;
    }

    public boolean isFavorite() {

        return favorite;
    }

    public void setFavorite(boolean favorite) {

        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
