export interface Innovator{
    innovatorId:string,
    username: string,
    firstName: string,
    lastName:string,
    avatarUrl: string,
    email: string,
    occupation?:string,
    updatedBy?:string,
    updatedOn?:Date,
    tags?:string[]
}