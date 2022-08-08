package com.stackroute.innovator;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Document
public class Innovator {
    private String innovatorId;
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarUrl;
    private String occupation;
    private String updatedBy;
    private Date updatedOn;
    private String[] tags;

    public Innovator(String innovatorId, String username, String firstName, String lastName, String email, String avatarUrl, String occupation, String updatedBy, Date updatedOn, String[] tags) {
        this.innovatorId = innovatorId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.occupation = occupation;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.tags = tags;
    }

    public Innovator() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Innovator)) return false;
        Innovator innovator = (Innovator) o;
        return getInnovatorId().equals(innovator.getInnovatorId()) && getUsername().equals(innovator.getUsername()) && getFirstName().equals(innovator.getFirstName()) && getLastName().equals(innovator.getLastName()) && getEmail().equals(innovator.getEmail()) && Objects.equals(getAvatarUrl(), innovator.getAvatarUrl()) && Objects.equals(getOccupation(), innovator.getOccupation()) && Objects.equals(getUpdatedBy(), innovator.getUpdatedBy()) && Objects.equals(getUpdatedOn(), innovator.getUpdatedOn()) && Arrays.equals(getTags(), innovator.getTags());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getInnovatorId(), getUsername(), getFirstName(), getLastName(), getEmail(), getAvatarUrl(), getOccupation(), getUpdatedBy(), getUpdatedOn());
        result = 31 * result + Arrays.hashCode(getTags());
        return result;
    }

    public String toString() {
        String var10000 = this.innovatorId;
        return "Innovator{innovatorId='" + var10000 + "', username='" + this.username + "', firstName='" + this.firstName + "', lastName='" + this.lastName + "', email='" + this.email + "', avatarUrl='" + this.avatarUrl + "', occupation='" + this.occupation + "', updatedBy='" + this.updatedBy + "', updatedOn=" + this.updatedOn + ", tags=" + Arrays.toString(this.tags) + "}";
    }

    public String getInnovatorId() {
        return this.innovatorId;
    }

    public void setInnovatorId(String innovatorId) {
        this.innovatorId = innovatorId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String[] getTags() {
        return this.tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}

