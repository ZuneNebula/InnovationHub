package com.stackroute.expert;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Document
public class Expert {
    private String expertId;
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
    private String[] specialization;
    private float rating;

    public Expert(String expertId, String username, String firstName, String lastName, String email, String avatarUrl, String occupation, String updatedBy, Date updatedOn, String[] tags, String[] specialization, float rating) {
        this.expertId = expertId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.occupation = occupation;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.tags = tags;
        this.specialization = specialization;
        this.rating = rating;
    }

    public Expert() {
    }

    public String toString() {
        String var10000 = this.expertId;
        return "Expert{expertId='" + var10000 + "', username='" + this.username + "', firstName='" + this.firstName + "', lastName='" + this.lastName + "', email='" + this.email + "', avatarUrl='" + this.avatarUrl + "', occupation='" + this.occupation + "', updatedBy='" + this.updatedBy + "', updatedOn=" + this.updatedOn + ", tags=" + Arrays.toString(this.tags) + ", specialization='" + this.specialization + "', rating=" + this.rating + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expert)) return false;
        Expert expert = (Expert) o;
        return Float.compare(expert.getRating(), getRating()) == 0 && getExpertId().equals(expert.getExpertId()) && getUsername().equals(expert.getUsername()) && getFirstName().equals(expert.getFirstName()) && getLastName().equals(expert.getLastName()) && getEmail().equals(expert.getEmail()) && Objects.equals(getAvatarUrl(), expert.getAvatarUrl()) && Objects.equals(getOccupation(), expert.getOccupation()) && Objects.equals(getUpdatedBy(), expert.getUpdatedBy()) && Objects.equals(getUpdatedOn(), expert.getUpdatedOn()) && Arrays.equals(getTags(), expert.getTags()) && Objects.equals(getSpecialization(), expert.getSpecialization());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getExpertId(), getUsername(), getFirstName(), getLastName(), getEmail(), getAvatarUrl(), getOccupation(), getUpdatedBy(), getUpdatedOn(), getSpecialization(), getRating());
        result = 31 * result + Arrays.hashCode(getTags());
        return result;
    }

    public String getExpertId() {
        return this.expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
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

    public String[] getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(String[] specialization) {
        this.specialization = specialization;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}

