import { EntityModel } from "./_entity.model";

export class UserModel extends EntityModel {
    id: number;
    username: string;
    name: string;
    role: string;
    email: string;
}