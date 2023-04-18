export interface Class {
    classId: number;
    name: string;
    description: string;
    teacher: number;
}

export interface ClassGroup {
    classGroupId: number;
    name: string;
    description: string;
    classObj: number;
}

export interface Topic {
    topicId: number;
    name: string;
    description: string;
    classObj: number;
}

export interface User {
    id: number;
    username: string;
    password: string;
    firstname: string;
    lastname: string;
    email: string;
    phoneNumber: string;
    role: string;
  }