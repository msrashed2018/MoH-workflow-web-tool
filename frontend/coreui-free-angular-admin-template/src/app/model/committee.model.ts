import { CommitteeMember } from './committee-member.model';
import { Zone } from './zone.model';

export class Committee {
    public id : number;
    public name: string;
    public description: string;
    public date: string;
    public type: string;
    public function: string;
    public zone: Zone;
    public memberOneId : CommitteeMember;
    public memberTwoIdid : CommitteeMember;
    public memberThreeId : CommitteeMember;
    public memberFourId : CommitteeMember;
    public memberFiveId : CommitteeMember;
    public memberSixId : CommitteeMember;
}