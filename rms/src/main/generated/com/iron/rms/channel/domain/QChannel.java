package com.iron.rms.channel.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChannel is a Querydsl query type for Channel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChannel extends EntityPathBase<Channel> {

    private static final long serialVersionUID = -511404141L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChannel channel = new QChannel("channel");

    public final ListPath<com.iron.rms.activity.domain.Activity, com.iron.rms.activity.domain.QActivity> activities = this.<com.iron.rms.activity.domain.Activity, com.iron.rms.activity.domain.QActivity>createList("activities", com.iron.rms.activity.domain.Activity.class, com.iron.rms.activity.domain.QActivity.class, PathInits.DIRECT2);

    public final EnumPath<com.iron.rms.constant.ApprovalState> approvalState = createEnum("approvalState", com.iron.rms.constant.ApprovalState.class);

    public final NumberPath<Long> channelId = createNumber("channelId", Long.class);

    public final StringPath channelName = createString("channelName");

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final com.iron.rms.member.domain.QMember member;

    public final ListPath<com.iron.rms.party.domain.Party, com.iron.rms.party.domain.QParty> parties = this.<com.iron.rms.party.domain.Party, com.iron.rms.party.domain.QParty>createList("parties", com.iron.rms.party.domain.Party.class, com.iron.rms.party.domain.QParty.class, PathInits.DIRECT2);

    public QChannel(String variable) {
        this(Channel.class, forVariable(variable), INITS);
    }

    public QChannel(Path<? extends Channel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChannel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChannel(PathMetadata metadata, PathInits inits) {
        this(Channel.class, metadata, inits);
    }

    public QChannel(Class<? extends Channel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.iron.rms.member.domain.QMember(forProperty("member")) : null;
    }

}

