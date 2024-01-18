package com.iron.rms.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1223722977L;

    public static final QMember member = new QMember("member1");

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath memberName = createString("memberName");

    public final StringPath memberPw = createString("memberPw");

    public final EnumPath<com.iron.rms.constant.MemberType> memberType = createEnum("memberType", com.iron.rms.constant.MemberType.class);

    public final ListPath<com.iron.rms.party.domain.Party, com.iron.rms.party.domain.QParty> parties = this.<com.iron.rms.party.domain.Party, com.iron.rms.party.domain.QParty>createList("parties", com.iron.rms.party.domain.Party.class, com.iron.rms.party.domain.QParty.class, PathInits.DIRECT2);

    public final StringPath phone = createString("phone");

    public final ListPath<com.iron.rms.reservation.domain.Reservation, com.iron.rms.reservation.domain.QReservation> reservations = this.<com.iron.rms.reservation.domain.Reservation, com.iron.rms.reservation.domain.QReservation>createList("reservations", com.iron.rms.reservation.domain.Reservation.class, com.iron.rms.reservation.domain.QReservation.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

