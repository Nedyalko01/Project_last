package com.example.moonlighthotel.model;

import com.example.moonlighthotel.enumerations.RoomType;
import com.example.moonlighthotel.enumerations.RoomView;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomType title;

    @NotNull
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Set<Image> images;

    @NotNull
    private String description;

//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private BedType facilities;

    @NotNull
    private Integer area;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomView roomView;

    @NotNull
    private Integer people;

    @NotNull
    private Double price;

    @OneToMany(mappedBy = "room")
    private List<RoomReservation> roomReservation;

    private Integer count;

    public Room() {
    }

    public Room(Long id, @NotNull RoomType title, @NotNull String image, @NotNull Set<Image> images,
                @NotNull String description, @NotNull Integer area, @NotNull RoomView roomView,
                @NotNull Integer people, @NotNull Double price, List<RoomReservation> roomReservation,
                Integer count) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.images = images;
        this.description = description;
        this.area = area;
        this.roomView = roomView;
        this.people = people;
        this.price = price;
        this.roomReservation = roomReservation;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getTitle() {
        return title;
    }

    public void setTitle(RoomType title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public RoomView getRoomView() {
        return roomView;
    }

    public void setRoomView(RoomView roomView) {
        this.roomView = roomView;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<RoomReservation> getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(List<RoomReservation> roomReservation) {
        this.roomReservation = roomReservation;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
