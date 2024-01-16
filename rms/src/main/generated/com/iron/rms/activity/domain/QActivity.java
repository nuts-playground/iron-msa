package com.iron.rms.activity.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivity is a Querydsl query type for Activity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivity extends EntityPathBase<Activity> {

    private static final long serialVersionUID = 44190217L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivity activity = new QActivity("activity");

    public final NumberPath<Long> activityId = createNumber("activityId", Long.class);

    public final NumberPath<Integer> activityMin = createNumber("activityMin", Integer.class);

    public final StringPath activityName = createString("activityName");

    public final com.iron.rms.channel.domain.QChannel channel;

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final ListPath<com.iron.rms.slot.domain.Slot, com.iron.rms.slot.domain.QSlot> slots = this.<com.iron.rms.slot.domain.Slot, com.iron.rms.slot.domain.QSlot>createList("slots", com.iron.rms.slot.domain.Slot.class, com.iron.rms.slot.domain.QSlot.class, PathInits.DIRECT2);

    public QActivity(String variable) {
        this(Activity.class, forVariable(variable), INITS);
    }

    public QActivity(Path<? extends Activity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActivity(PathMetadata metadata, PathInits inits) {
        this(Activity.class, metadata, inits);
    }

    public QActivity(Class<? extends Activity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.channel = inits.isInitialized("channel") ? new com.iron.rms.channel.domain.QChannel(forProperty("channel"), inits.get("channel")) : null;
    }

}

