package com.iron.rms.slot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSlot is a Querydsl query type for Slot
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSlot extends EntityPathBase<Slot> {

    private static final long serialVersionUID = -155462169L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSlot slot = new QSlot("slot");

    public final com.iron.rms.activity.domain.QActivity activity;

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final StringPath date = createString("date");

    public final ListPath<com.iron.rms.reservation.domain.Reservation, com.iron.rms.reservation.domain.QReservation> reservations = this.<com.iron.rms.reservation.domain.Reservation, com.iron.rms.reservation.domain.QReservation>createList("reservations", com.iron.rms.reservation.domain.Reservation.class, com.iron.rms.reservation.domain.QReservation.class, PathInits.DIRECT2);

    public final NumberPath<Long> slotId = createNumber("slotId", Long.class);

    public final StringPath time = createString("time");

    public QSlot(String variable) {
        this(Slot.class, forVariable(variable), INITS);
    }

    public QSlot(Path<? extends Slot> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSlot(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSlot(PathMetadata metadata, PathInits inits) {
        this(Slot.class, metadata, inits);
    }

    public QSlot(Class<? extends Slot> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new com.iron.rms.activity.domain.QActivity(forProperty("activity"), inits.get("activity")) : null;
    }

}

