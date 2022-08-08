import { NotificationData } from "./notificationData";
export interface Notification{
    notificationId:string;
    sourceType:string;
    sourceId:string;
    targetType:string;
    targetId:string;
    status:string;
    innovationId:string;
    proposalId:string;
    notificationData:NotificationData;
}