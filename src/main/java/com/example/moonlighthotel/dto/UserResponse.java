package com.example.moonlighthotel.dto;


import java.time.Instant;
import java.util.Set;

public class UserResponse {

    private Long id;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private Set<String> roles;
    private Instant created;

    private UserResponse(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.name = builder.name;
        this.surname = builder.surname;
        this.phone = builder.phone;
        this.roles = builder.roles;
        this.created = builder.created;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public Instant getCreated() {
        return created;
    }

    public static class Builder {

        private Long id;
        private String email;
        private String name;
        private String surname;
        private String phone;
        private Set<String> roles;
        private Instant created;

        public Builder addId(Long id) {
            this.id = id;
            return this;
        }

        public Builder addEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder addPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder addRoles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        public Builder addCreated(Instant created) {
            this.created = created;
            return this;
        }

        public UserResponse build() {
            return new UserResponse(this);
        }
    }

}
